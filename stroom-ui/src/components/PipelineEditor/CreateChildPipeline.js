import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'semantic-ui-react';
import Tooltip from 'components/Tooltip';

const CreateChildPipeline = ({ pipelineId, startInheritedPipeline }) => (
  <Tooltip
    trigger={
      <Button
        className="icon-button"
        floated="right"
        circular
        icon="recycle"
        onClick={() => startInheritedPipeline(pipelineId)}
      />
    }
    content="Create a child pipeline, using this one as a parent"
  />
);

CreateChildPipeline.propTypes = {
  pipelineId: PropTypes.string.isRequired,
  startInheritedPipeline: PropTypes.func.isRequired,
};

export default CreateChildPipeline;
